import axios from 'axios'

const USERS_REST_API_URL = 'http://localhost:8080/api/users';

class UserService {

    getUsers(){
        return axios.get(USERS_REST_API_URL);
    }

    addUser(user){
        return axios.post(USERS_REST_API_URL, user);
    }
}

export default new UserService();

