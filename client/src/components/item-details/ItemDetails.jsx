import React, { useEffect, useState } from 'react';
import './item-details.css'; // Assuming you have the CSS in a separate file
import { useGetOneItem } from '../../hooks/useItems';
import { usePutItemInCart } from '../../hooks/useOrders';
import { useParams } from 'react-router-dom';
import { useAuthContext } from '../../contexts/AuthContext';
import ratingsAPI from '../../api/rating-api';

const initialValues = {
    userId: '',
    itemId: '',
    quantity: ''
};

const ItemDetails = () => {
    const [quantity, setQuantity] = useState(1);
    const [rating, setRating] = useState(5);
    const {itemId} = useParams();
    const {userId} = useAuthContext();
    const [isBought, setIsBought] = useState(false);
    const [isRate, setIsRate] = useState(false);
    const [item, setItem] = useGetOneItem(itemId);

    
 
    initialValues.itemId = itemId;
    initialValues.userId = userId;



    const putInCart = usePutItemInCart();

    const ratingSubmitHandler = async (e) => {
      e.preventDefault();

     const ratetItem = await ratingsAPI.rate(itemId, rating, userId);
     setIsRate(true)
     setTimeout(() => {
         setIsRate(false);
       }, 3000);
      
     setItem(ratetItem);
    }

    useEffect(() => {
      if (quantity > 10) {
        setQuantity(10);
      }
      if (quantity < 1) {
        setQuantity(1);
      }
    },[quantity]);


    
  
    const handleQuantityChange = (e) => {
      setQuantity(e.target.value);
    };
  
    const addToCart = () => {
        initialValues.quantity = quantity;
        putInCart(initialValues)
        setIsBought(true)
        setTimeout(() => {
            setIsBought(false);
          }, 3000);
    };
  
    const handleRatingClick = (newRating) => {
      setRating(newRating);
     
    };

  
    return (
      <div className="item-container">
        <div className="item-details">
          <div className="item-image">
            <img src={item.imageURL} alt="Item" />
            <div className="rating">
            <button className="rate-button" onClick={ratingSubmitHandler}  >Rate</button>
              {[5, 4, 3, 2, 1].map((star) => (
                <button
                  key={star}
                  className={`star ${star <= rating ? 'filled' : ''}`}
                  onClick={() => handleRatingClick(star)}
                >
                  ★
                </button>
              ))}
              <small className='raiting-small'>{rating}</small>         
              {isRate && <p className='is-rated'>!Thanks for the rating</p>}     
              
            </div>
          </div>
          <div className="item-info">
            <div className='header-div'>
            <h1 className="item-title">{item.name} </h1>
            <h1 className='item-rate'>{item.rating} ★</h1>
            </div>
            
            <p className="item-description">
              {item.description}
            </p>
            <div className="action-section">
                <p className="item-price">{item.price}€</p>
                <div className="quantity-selector">
                <input 
                  type="number" 
                  id="quantity" 
                  name="quantity" 
                  min="1" 
                  max='10'
                  value={quantity} 
                  onChange={handleQuantityChange} 
                />
              </div>
              <button className="add-to-cart-button" onClick={addToCart}>Add to Cart</button>
            </div>
            {isBought ?<p className='succes-boutht'>You successfully added to cart {quantity} {item.name}!</p> :<></>}
          </div>
        </div>
      </div>
    );
  };
  
  export default ItemDetails;
