//import { render } from '@testing-library/react';
import React from 'react';
import UserService from '../services/UserService'; 
// import axios from 'axios';



class UserComponent extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            users:[]
        }
    }
    //componentdidmount method invoked immidiatly after a comp. is mounted
    componentDidMount(){
        UserService.getUsers().then((Response) => {
            this.setState({users: Response.data})
        });
    }

render() {
    return(
        <div>
                <h2 className = "text-center"> REST Extracted List</h2>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                            <td> User Id</td>
                            <td> User First Name</td>
                            <td> User Last Name</td>
                            <td> User Email Id</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.users.map(
                                user => 
                                <tr key = {user.id}>
                                     <td> {user.id}</td>   
                                     <td> {user.firstName}</td>   
                                     <td> {user.lastName}</td>   
                                     <td> {user.email}</td>   
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
           )
}
}
export default UserComponent