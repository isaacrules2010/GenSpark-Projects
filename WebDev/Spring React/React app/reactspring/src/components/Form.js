import React from 'react'
import '../App.css'
import UserService from '../services/UserService';
import UserComponent from './UserComponent';

class Form extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            id: null,
            firstName: null,
            lastName: null,
            email: null
        }
    }
    componentDidMount(){
        UserService.getUsers();
    }

    createStudent = async (first, last, em) => {
        let res = await UserService.post("", {firstName:first, lastName:last, email:em});
        console.log(res);
        UserService.getUsers();
    }

    handleFirstName = event =>{
        this.setState({firstName: event.target.value});
    }

    handleLastName = event =>{
        this.setState({lastName: event.target.value});
    }

    handleEmail = event => {
        this.setState({email: event.target.value});
    }

    addFromForm = () => {
        if(this.state !== null){
            console.log(this.state);
            UserService.addUser(this.state);
            window.location.reload(false);
        }
    }
  render() {
    return (
      <div>
        <h1> Add Users </h1>
        <div>   
            <div class='userInput'>
                <p class='label'>First Name</p>
                <input type='text' id='firstNameInput' title='First Name' onChange={this.handleFirstName}/>
            </div>
            <div class='userInput'>
                <p class='label'>Last Name</p>
                <input type="text" id='lastNameInput' title='Last Name' pattern='\d*' onChange={this.handleLastName}/>
            </div>
            <div class='userInput'>
                <p class='label'>Email</p>
                <input type="email" id='emailInput' title='Email' onChange={this.handleEmail}/>
            </div>
            <button onClick={this.addFromForm}>Submit</button>
        </div>
      </div> 
    )
  }
}

export default Form

