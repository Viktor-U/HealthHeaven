import React, { useEffect, useState } from 'react';
import './cart.css';
import useGetAllItemsInCart, { useDelItemInCart } from '../../hooks/useOrders';
import { useAuthContext } from '../../contexts/AuthContext';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';

const initialValues = {
    userId: '',
    itemId: '',
};

function Cart() {
    const { userId } = useAuthContext();
    const [items, setItems] = useGetAllItemsInCart(userId);
    const [totalPrice, setTotalPrice] = useState(0);
    const delInCart = useDelItemInCart();
    const { t, i18n } = useTranslation();

    initialValues.userId = userId;

    const handleBuyAllClick = () => {
        alert(t('all_items_ordered'));
    };

    useEffect(() => {
        if(i18n.language !== 'bg'){
            const total = items.reduce((sum, item) => sum + item.price * item.quantity, 0);
            setTotalPrice(total);
        }else{
            const total = items.reduce((sum, item) => sum + (item.price * 1.96) * item.quantity, 0);
            setTotalPrice(total);
         };
    }, [items, i18n.language]);



    const handleRemoveClick = async (id) => {
        initialValues.itemId = id;
        setItems(prevItems => {
            return prevItems.map(item => {
                if (item.id === id) {
                    const updatedQuantity = item.quantity - 1;
                    if (updatedQuantity <= 0) {
                        return null;
                    }
                    return { ...item, quantity: updatedQuantity };
                }
                return item;
            }).filter(item => item !== null);
        });
        await delInCart(initialValues);
    };


    return (
        <section className="cart">
            <h2>{t('your_cart')}</h2>
            {items.length === 0 ? (
                <p>{t('cart_empty')}</p>
            ) : (
                <div className="cart-items">
                    {items.map(item => (
                        <div key={item.id} className="cart-item">
                            <Link to={`/items/${item.id}/details`}><img src={item.imageURL} alt={item.name} /></Link>
                            <div className="item-details">
                                <div>
                                    <h3>{item.name}</h3>
                                    <p>{t('price')}: {i18n.language === 'bg' ? `${(item.price *1.96).toFixed(2)} лв.` : `${(item.price).toFixed(2)}€`}</p>
                                </div>
                                <h4>{item.quantity}</h4>
                            </div>
                            <div className="item-buttons">
                                <button className="remove-button" onClick={() => handleRemoveClick(item.id)}>{t('remove')}</button>
                            </div>
                        </div>
                    ))}
                </div>
            )}
            <button className="buy-button" onClick={handleBuyAllClick}>{t('buy_now', { totalPrice})}</button>
        </section>
    );
}

export default Cart;
