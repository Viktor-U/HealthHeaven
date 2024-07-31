async function requester(method, url, data){
    const options = {};

    const accessToken = localStorage.getItem('accesssToken')

    if (accessToken) {
        options.headers = {
            ...options.headers,
            'token': accessToken
        }
    }

    if (method !== 'GET'){
        options.method = method;
    }

    if (data) {
        options.headers = {
            ...options.headers,
            'Content-Type': 'application/json',
        };
       
        options.body = JSON.stringify(data);
    }

    const respones = await fetch(url, options);
    if (!respones.status === 204) {
        return;
    }

    const result = await respones.json();

    if (!respones.ok) {
        throw result;
    }
    return result;
};

export const get = requester.bind(null, 'GET')
export const post = requester.bind(null, 'POST')
export const put = requester.bind(null, 'PUT')
export const del = requester.bind(null, 'DELETE')


export default {
    get,
    post,
    put,
    del,
};