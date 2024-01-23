Vue.component("log-in", 
{
		data : function()
	{
		return{
			user : {
				
				username : null,
				password : null,
			},		
			
		    errorMessages: {
	        username: " ",
	        password: " ",
      },
      registeredUsers : null
		}
	},
template:
`   <div class="login-container">
    <h2>Login</h2>
    <form class="login-form" @submit = "TryLogin">
          <small>{{ errorMessages.username }}</small>
      <input type="text" placeholder="Username" v-model = "user.username">
            <small>{{ errorMessages.password }}</small>
      <input type="password" placeholder="Password" v-model = "user.password">
      <input type="submit" value="Login">
    </form>
    <div class="form-footer">
      <a href="#/register">Register</a>
    </div>
  </div>`
, methods :{
	
	 TryLogin() {
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

      if (valid) {
        const foundUser = this.registeredUsers.find(
          (user) =>
            user.username === this.user.username &&
            user.password === this.user.password
        );

        if (foundUser) {
		 if (foundUser.role === 'Admin') {
			 this.$router.push("/" + foundUser.username + "/adminPage");
			 } else {
				 this.$router.push("/" + foundUser.username);
			 }
		 } else {

          this.errorMessages.username = "Invalid username or password.";
        }
      }
    }
},
	mounted () {
        axios
          .get('rest/users/getRegistered')
          .then(response => (this.registeredUsers = response.data)) 
    }
});
