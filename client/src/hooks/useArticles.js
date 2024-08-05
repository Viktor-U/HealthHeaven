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

export function  useCreateArticle() {
    const articleCreateHandler = (articleData) => articlesAPI.create(articleData);
      
    return articleCreateHandler;

}

export function  useDeleteArticle (articleId) {
    
    articlesAPI.del(articleId);
    
}