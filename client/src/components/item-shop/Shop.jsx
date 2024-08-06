import useGetAllItems from '../../hooks/useItems';
import Item from './shop-item/Item';
import './shop.css';
import { useTranslation } from 'react-i18next';

export default function Shop() {
    const [items] = useGetAllItems();
    const { t } = useTranslation();

    return (
        <section id="shop-page">
            <h1>{t('shop')}</h1>
            <div className='products'>
                {items.length > 0 
                    ? items.map(item => <Item key={item.id} {...item} />)
                    : <h3 className="no-articles">{t('no_products_yet')}</h3>
                }
            </div>
        </section>
    );
}
