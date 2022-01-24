

import { getCookie, setCookie } from "./util";


const BASE_URL = 'http://localhost:8080/'


function login(username, password) {
    const url = BASE_URL + 'api/v1/auth/login';
    const requestInit = {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Origin': 'http://localhost:3000'},
        body: JSON.stringify({
            'username': username,
            'password': password,
        })
    }

    fetch(url, requestInit)
    .then(res => {
        if(res.ok) {
            return res.json();
            
        }
        else {
            Promise.reject();
        }
    })
    .then(data => {
        setCookie('token', data.token, data.expiresIn)
        document.location.href = '/home';
    })
    .catch(() => console.log('Failed to sign up, try again'));
}

function signUp(username, password, email, phoneNumber) {
    const url = BASE_URL + 'api/v1/auth/register';
    const requestInit = {
        method: 'POST',
        headers: {'Content-Type': 'application/json', 'Origin': 'http://localhost:3000'},
        body: JSON.stringify({
            'username': username,
            'password': password,
            'email': email,
            'phoneNumber': phoneNumber,
            'suburb': ''
        })
    }

    fetch(url, requestInit)
    .then(res => {
        console.log(res);
        if(res.ok) {
            return res.json();
        }
        else {
            Promise.reject();
        }
    })
    .then(data => {
        setCookie('token', data.token, data.expiresIn)
        document.location.href = '/home';
    })
    .catch(() => console.log('Failed to sign up, try again'));
}

function isLoggedIn() {
    return getCookie('token') !== '';
}

export {
    login,
    signUp,
    isLoggedIn
}