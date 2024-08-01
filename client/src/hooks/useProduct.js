import { useEffect, useState } from "react";
import shopAPI from "../api/shop-api";

export default function useGetAllItems() {
    const [items, setItems] = useState([]);

    useEffect(() => {
        (async () =>{
            const result =await shopAPI.getAll();
            setItems(result);
        })();
    }, [])

    return [items, setItems];
}