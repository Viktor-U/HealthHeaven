import requester from "./requester"

const BASE_URL = 'http://localhost:8080/rate';

const buildUrl = (itemId) => `${BASE_URL}/${itemId}`;

const rate = async (itemId, rating ,raterId) => requester.post(buildUrl(itemId), {rating, raterId});

const ratingsAPI = {
    rate,
  
};

export default ratingsAPI;