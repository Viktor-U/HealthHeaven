import { useEffect, useState } from "react";
import articlesAPI from "../api/articles-api";

export default function useGetAllArticles() {
    const [articles, setArticles] = useState([]);

    useEffect(() => {
        (async () =>{
            const result =await articlesAPI.getAll();
            setArticles(result);
        })();

      

    }, [])

    return [articles, setArticles];
}