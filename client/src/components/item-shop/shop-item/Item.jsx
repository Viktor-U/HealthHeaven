import { useNavigate } from 'react-router-dom';
import '../shop.css';
import { useAuthContext } from '../../../contexts/AuthContext';
import { usePutItemInCart } from '../../../hooks/useOrders';
import { useState } from 'react';
import { useTranslation } from 'react-i18next';
import i18n from '../../../utils/i18n';

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
    const { isAuthenticated, userId } = useAuthContext();
    const { t } = useTranslation();
    const navigate = useNavigate();
    const putInCart = usePutItemInCart();


    const addToCart = () => {
        initialValues.itemId = id;
        initialValues.userId = userId;
        initialValues.quantity = 1;
        putInCart(initialValues);
        setIsBought(true);
        setTimeout(() => {
            setIsBought(false);
        }, 3000);
    };

    const handleDetailsClick = (id) => {
        navigate(`/items/${id}/details`);
    };
    if(i18n.language === 'bg'){
        price = (price*1.96).toFixed(2) ;
    }else{
        price = price.toFixed(2)
    };

    return (
        <div className="product">
            <img src={imageURL} alt={name} />
            <h3>{name}</h3>
            {isBought && <p className='succes-boutht one-item'>{t('successfully_added1')}</p>}
            <p>{t('price_currency', { price })}</p>
            {isAuthenticated
                ? (
                    <>
                        <button onClick={addToCart}>{t('add_to_cart')}</button>
                        <button onClick={() => handleDetailsClick(id)}>{t('details')}</button>
                    </>
                ) : (
                    <>
                        <button onClick={() => navigate("/login")}>{t('add_to_cart')}</button>
                        <button onClick={() => navigate("/login")}>{t('details')}</button>
                    </>
                )
            }
        </div>
    );
}
