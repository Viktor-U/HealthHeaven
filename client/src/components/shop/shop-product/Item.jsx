import '../shop.css';

export default function Item({
    id,
    productName,
    imageURL,
    price
}) {

    return(
        
            <div className="product">
                <img src={imageURL} alt={productName}/>
                <h3>{productName}</h3>
                <p>{price}</p>
                <button>Add to Cart</button>
                <button>Detailst</button>
            </div>
    );
}