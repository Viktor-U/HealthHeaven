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

export function useGetOneArticle(articleId) {
    const [article, setArticle] = useState({});
    
    useEffect(() => {
        (async () => {
            const result = await articlesAPI.getOne(articleId);

            setArticle(result);
        })();
    }, [articleId]);
    return[
        article,
        setArticle,
    ];
}