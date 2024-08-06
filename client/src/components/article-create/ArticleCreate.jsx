import './article-create.css';
import { useForm } from '../../hooks/useForm';
import { useNavigate } from 'react-router-dom';
import { useCreateArticle } from '../../hooks/useArticles';
import { useAuthContext } from '../../contexts/AuthContext';
import { useTranslation } from 'react-i18next';

const initialValues = {
    title: '',
    imageURL: '',
    content: '',
    author: '',
};

export default function ArticleCreate () {
  const navigate = useNavigate();
  const { email } = useAuthContext();
  const { t } = useTranslation();
  initialValues.author = email;
  
  const createArticle = useCreateArticle();
 
  const createHandler = async (values) => {
    const { id: articleId } = await createArticle(values);
    navigate(`/articles/${articleId}`);
  };

  const {
      values,
      changeHandler,
      submitHandler,
  } = useForm(initialValues, createHandler);

  return (
    <div className="article-create-page">
      <h1>{t('create_new_article')}</h1>
      <form onSubmit={submitHandler}>
        <div className="form-group">
          <label htmlFor="title">{t('title')}</label>
          <input
            type="text"
            id="title"
            name="title"
            value={values.title}
            onChange={changeHandler}
            minLength="2"
            maxLength="20"
            required
            placeholder={t('enter_article_title')}
          />
        </div>
        <div className="form-group">
          <label htmlFor="imageURL">{t('image_url')}</label>
          <input
            type="url"
            id="imageURL"
            name="imageURL"
            value={values.imageURL}
            onChange={changeHandler}
            required
            placeholder={t('upload_photo')}
          />
        </div>
        <div className="form-group">
          <label htmlFor="content">{t('content')}</label>
          <textarea
            id="content"
            name="content"
            value={values.content}
            onChange={changeHandler}
            required
          ></textarea>
        </div>
        <button className="article-create-button" type="submit">{t('create_article')}</button>
      </form>
    </div>
  );
};
