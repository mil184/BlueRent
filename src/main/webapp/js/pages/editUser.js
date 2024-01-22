Vue.component("editUser", {
  data() {
    return {
      username: this.$route.params.username,
      user : {
				username : null,
				password : null,
				firstName : null,
				lastName : null,
				gender: null,
				dateOfBirth : null,
			}
    };
  },
  template: `
    <div>
      <h3>HELLO {{ user.username }}</h3>
      <form class="login-form" @submit="TryEdit">
  
      <input type="text" v-model="user.username" value="text">
      
      <input type="password" placeholder="Password" v-model="user.password">
      
      <input type="text" placeholder="First Name" v-model="user.firstName">
      
      <input type="text" placeholder="Last Name" v-model="user.lastName">
      
      <select v-model="user.gender">
        <option value="" disabled selected>Select Gender</option>
        <option value="male">Male</option>
        <option value="female">Female</option>
      </select>
      
      <input type="date" placeholder="Date of Birth" v-model="user.dateOfBirth">

    <input type="submit" value="Update">
  </form>
    </div>
  `,
  mounted() {
    axios.get("rest/users/getUser/" + this.username).then(response => (this.user = response.data));
  },
  methods: {
	  TryEdit(){
		  axios
					.post('rest/users/edit/' + this.username, 
					{"username": this.user.username,
					 "password": this.user.password,
					 "firstName" : this.user.firstName,
					 "lastName" : this.user.lastName,
					 "gender" : this.user.gender,
					 "dateOfBirth" : this.user.dateOfBirth
					}).catch(error => 
		    			console.error(error));
	  }
  }
});