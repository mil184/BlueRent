Vue.component("userPage", {
  data() {
    return {
      username: this.$route.params.username,
      user : {
		  username : this.$route.params.username,
		  password : null,
		  firstName : null,
		  lastName : null,
		  gender : null,
		  dateOfBirth : null,
		  role : null,
		  type : null
	  }
    };
  },
  template: `
    <div>
      <h3>HELLO {{ username }}</h3>
      <p>{{ user.firstName }} {{ user.lastName }}</p>
      <p>{{ user.gender }}</p>
      <p>{{ user.dateOfBirth }}</p>
      <p>{{ user.role }}</p>
      <p>{{ user.type }}</p>
      <button v-on:click="editUser()">Edit</button>
    </div>
  `,
  mounted() {
    axios.get("rest/users/getUser/" + this.username).then(response => (this.user = response.data));
  },
  
  methods:{
	  editUser: function(){
		  this.$router.push(`/` + this.username + `/edit`);
	  }
  }
});