import React, { useEffect, useState } from 'react';
import './cart.css';
import useGetAllItemsInCart, { useDelItemInCart } from '../../hooks/useOrders';
import { useAuthContext } from '../../contexts/AuthContext';
import { useNavigate } from 'react-router-dom';

const initialValues = {
    userId: '',
    itemId: '',
    quantity: 1,
};

function Cart() {
    const {userId} = useAuthContext();

    const [items, setItems] = useGetAllItemsInCart(userId);
    const [totalPrice, setTotalPrice] = useState(0);
    const delInCart = useDelItemInCart();
    const navigate = useNavigate();

    initialValues.userId = userId;

    const handleBuyAllClick = () => {
        alert('All items have been ordered!');
    };
    useEffect(() => {
        // Calculate the total price whenever items change
        const total = items.reduce((sum, item) => sum + item.price * item.quantity, 0);
        setTotalPrice(total);
    }, [items]);

    const handleRemoveClick = async (id) => {
        initialValues.itemId = id;
        if (items.quantity === 0) {
            
            setItems(items.filter(item => item.id !== id));
        }
        await delInCart(initialValues);
        navigate("/cart");
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
                            <img src={item.imageURL} alt={item.name} />
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
