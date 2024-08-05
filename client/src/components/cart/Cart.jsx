import React, { useEffect, useState } from 'react';
import './cart.css';
import useGetAllItemsInCart, { useDelItemInCart } from '../../hooks/useOrders';
import { useAuthContext } from '../../contexts/AuthContext';
import { Link, useNavigate } from 'react-router-dom';

const initialValues = {
    userId: '',
    itemId: '',
};

function Cart() {
    const {userId} = useAuthContext();
    const [items, setItems] = useGetAllItemsInCart(userId);
    const [totalPrice, setTotalPrice] = useState(0);
    const delInCart = useDelItemInCart();

    initialValues.userId = userId;

    const handleBuyAllClick = () => {
        alert('All items have been ordered!');
    };
    useEffect(() => {
        const total = items.reduce((sum, item) => sum + item.price * item.quantity, 0);
        setTotalPrice(total);
    }, [items]);

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
            <h2>Your Cart</h2>
            {items.length === 0 ? (
                <p>Your cart is empty.</p>
            ) : (
                <div className="cart-items">
                    {items.map(item => (
                        <div key={item.id} className="cart-item">
                            <Link to={`/items/${item.id}/details`}><img src={item.imageURL} alt={item.name} /></Link>
                            <div className="item-details">
                                <div>
                                    <h3>{item.name}</h3>
                                    <p>Price: {item.price}€</p>
                                </div>
                                <h4>{item.quantity}</h4>
                            </div>
                            <div className="item-buttons">
                                <button className="remove-button" onClick={() => handleRemoveClick(item.id)}>Remove</button>
                            </div>
                        </div>
                    ))}
                </div>
            )}
            <button className="buy-button" onClick={handleBuyAllClick}>Buy Now - {totalPrice}€</button>
        </section>
    );
}

export default Cart;
