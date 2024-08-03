import useGetAllItems from '../../hooks/useItems';
import Item from './shop-item/Item';
import './shop.css';

export default function Shop() {

    const [items] = useGetAllItems();

    
    return(
        <section id="shop-page">
            <h1>Shop</h1>
            <div className='products'>
                {items.length > 0 
                    ? items.map(item => <Item key={item.id} {...item} />)
                    : <h3 className="no-articles">No products yet!</h3>
                }
            </div>
        </section>
    );
}