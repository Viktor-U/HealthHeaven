import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

const resources = {
  en: {
    translation: {
      "health_heaven": "Health Heaven",
      "all_doctors": "Doctors",
      "shop": "Shop",
      "articles": "Articles",
      "create_doctor": "Create Doctor",
      "my_cart": "My Cart",
      "logout": "Logout",
      "login": "Login",
      "register": "Register"
    }
  },
  bg: {
    translation: {
      "health_heaven": "Здравен Рай",
      "all_doctors": "Лекари",
      "shop": "Магазин",
      "articles": "Статии",
      "create_doctor": "Създаване на Лекар",
      "my_cart": "Моята Количка",
      "logout": "Изход",
      "login": "Вход",
      "register": "Регистрация"
    }
  }
};

i18n.use(initReactI18next).init({
  resources,
  lng: "en", // default language
  interpolation: {
    escapeValue: false // react already safes from xss
  }
});

export default i18n;
