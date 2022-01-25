import { getCookie } from "./util";

function fetchUserData(userId){
    const url = 'https://stark-ocean-44226.herokuapp.com/users/'+userId;
    const request = {
    method: 'GET',
    headers: {'Content-Type': 'application/json', 'Origin': 'http://localhost:3000','Authorization':'Bearer '+getCookie('token')},
    }

    fetch(url, request)
    .then(res => {
        if(res.ok) {
            return res.json();
        }
        else {
            Promise.reject();
        }
    })
    .then(data => {
    console.log(data)
    return data
    })    
}

export {
    fetchUserData
}