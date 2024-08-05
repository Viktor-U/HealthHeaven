import { useNavigate } from 'react-router-dom';
import '../shop.css';
import { useAuthContext } from '../../../contexts/AuthContext';
import { usePutItemInCart } from '../../../hooks/useOrders';
import { useState } from 'react';

const initialValues = {
    userId: '',
    itemId: '',
    quantity: ''
};

export default function Item({
    id,
    name,
    imageURL,
    price
}) {
    const [isBought, setIsBought] = useState(false);
    const {isAuthenticated, userId} = useAuthContext();
    

    const navigate = useNavigate();
    const putInCart = usePutItemInCart();

    const addToCart = () => {
        initialValues.itemId = id;
        initialValues.userId = userId;
        initialValues.quantity = 1;
        putInCart(initialValues)
        setIsBought(true)
        setTimeout(() => {
            setIsBought(false);
          }, 3000);
    };

    const handleDetailsClick = (id) => {
        navigate(`/items/${id}/details`)
    };
    

    return(
        
            <div className="product">
                <img src={imageURL} alt={name}/>
                <h3>{name}</h3>
                {isBought ?<p className='succes-boutht one-item'>Successfully added!</p> :<></>}
                <p>{price}€</p>
                {isAuthenticated
                    ?   
                        <>
                        <button onClick={addToCart}>Add to Cart</button>
                        <button onClick={() => handleDetailsClick(id)}>Detailst</button>
                        </>
                    :
                        <>
                        <button onClick={() => navigate("/login")}>Add to Cart</button>
                        <button onClick={() => navigate("/login")}>Detailst</button>
                        </>
                }
            </div>
    );
}