import './article-create.css';
import { useForm } from '../../hooks/useForm';
import { useNavigate } from 'react-router-dom';
import { useCreateArticle } from '../../hooks/useArticles';
import { useAuthContext } from '../../contexts/AuthContext';
import { useEffect } from 'react';


const initialValues = {
    title: '',
    imageURL: '',
    content: '',
    author: '',
};

export default function ArticleCreate () {
  const navigate = useNavigate();
  const {email} = useAuthContext();
  initialValues.author = email;
  
  const createArticle = useCreateArticle();
 
  const createHandler = async (values) => {

    const {id: articleId} = await createArticle(values);
    navigate(`/articles/${articleId}`);
  };

  const{
      values,
      changeHandler,
      submitHandler,
  } = useForm(initialValues, createHandler);



  return (
    <div className="article-create-page">
      <h1>Create a New Article</h1>
      <form onSubmit={submitHandler}>
        <div className="form-group">
          <label htmlFor="title">Title</label>
          <input
            type="text"
            id="title"
            name="title"
            value={values.title}
            onChange={changeHandler}
            minLength="2"
            maxLength="20"
            required
            placeholder="Enter article titel..."
          />
        </div>
        <div className="form-group">
          <label htmlFor="imageURL">Image URL</label>
          <input
            type="url"
            id="imageURL"
            name="imageURL"
            value={values.imageURL}
            onChange={changeHandler}
            required
            placeholder="Upload a photo..."
          />
        </div>
        <div className="form-group">
          <label htmlFor="content">Content</label>
          <textarea
            id="content"
            name="content"
            value={values.content}
            onChange={changeHandler}
            required
          ></textarea>
        </div>
        <button className="article-create-button"type="submit">Create Article</button>
      </form>
    </div>
  );
};

