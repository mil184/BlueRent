Vue.component("register", 
{
	data : function()
	{
		return{
			user : {
				
				username : null,
				password : null,
				firstName : null,
				lastName : null, 
				gender: null,
				dateOfBirth : null
			},
			
			confirmationPassword : null,
			
	    errorMessages: {
        username: " ",
        password: " ",
        firstName: " ",
        lastName: " ",
        gender: " ",
        dateOfBirth: " "
      }
		}
	},
template:
`
<div class="login-container">
  <h2>Registration</h2>
  <form class="login-form" @submit="TryRegister">
  
      <small>{{ errorMessages.username }}</small>
      <input type="text" placeholder="Username" v-model="user.username">
      
      <small>{{ errorMessages.password }}</small>
      <input type="password" placeholder="Password" v-model="user.password">
      
      <small>{{ errorMessages.confirmationPassword }}</small>
      <input type="password" placeholder="Confirm Password" v-model="confirmationPassword">
      
      <small>{{ errorMessages.firstName }}</small>
      <input type="text" placeholder="First Name" v-model="user.firstName">
      
      <small>{{ errorMessages.lastName }}</small>
      <input type="text" placeholder="Last Name" v-model="user.lastName">
      
      <small>{{ errorMessages.gender }}</small>
      <select v-model="user.gender">
        <option value="" disabled selected>Select Gender</option>
        <option value="male">Male</option>
        <option value="female">Female</option>
      </select>
      
      <small>{{ errorMessages.dateOfBirth }}</small>
      <input type="date" placeholder="Date of Birth" v-model="user.dateOfBirth">

    <input type="submit" value="Register">
  </form>
  <div class="form-footer">
    <a href="#">Forgot Password?</a>
    <span class="separator">|</span>
    <a href="#/login">Login</a>
  </div>
</div>

`,
methods:
{
		TryRegister() {
			
      let valid = true;
      this.errorMessages = {}; 

      if (!this.user.username) {
        valid = false;
        this.errorMessages.username = "Please enter a username.";
      }
      
      if (!this.user.password) {
        valid = false;
        this.errorMessages.password = "Please enter a password.";
      }
      
      if (!this.confirmationPassword) {
        valid = false;
        this.errorMessages.confirmationPassword = "Please confirm your password.";
      } else if (this.confirmationPassword !== this.user.password) {
        valid = false;
        this.errorMessages.confirmationPassword = "Passwords do not match.";
      }
      
      if (!this.user.firstName) {
        valid = false;
        this.errorMessages.firstName = "Please enter your first name.";
      }
      
      if (!this.user.lastName) {
        valid = false;
        this.errorMessages.lastName = "Please enter your last name.";
      }
      
      if (!this.user.gender) {
        valid = false;
        this.errorMessages.gender = "Please select your gender.";
      }
      if (!this.user.dateOfBirth) {
        valid = false;
        this.errorMessages.dateOfBirth = "Please enter your date of birth.";
      }
	
			if(valid){
					axios
					.post('rest/users/register', 
					{"username": this.user.username,
					 "password": this.user.password,
					 "firstName" : this.user.firstName,
					 "lastName" : this.user.lastName,
					 "gender" : this.user.gender,
					 "role" : 'Customer',
					 "dateOfBirth" : this.user.dateOfBirth,
					 "points": 0.0,
					 "type": 'Bronze'
					}).catch(error => 
		    			console.error(error));
    			}
	}
}
});
