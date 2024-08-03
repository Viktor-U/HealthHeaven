import { useEffect, useState } from "react";
import shopAPI from "../api/shop-api";

export default function useGetAllItemsInCart(userId) {
    const [items, setItems] = useState([]);

    useEffect(() => {
        (async () =>{
            const result =await shopAPI.getAllInCart(userId);
            setItems(result);
        })();
    }, [])

    return [items, setItems];
}

export function usePutItemInCart() {
    const itemPutInCartHandler = (order) => shopAPI.putInCart(order);
      
    return itemPutInCartHandler;

}