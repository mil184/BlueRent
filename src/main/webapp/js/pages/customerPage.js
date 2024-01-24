Vue.component("customerPage", {
  data() {
    return {
	  username: this.$route.params.username,
      user: {
        username: this.$route.params.username,
        password: null,
        firstName: null,
        lastName: null,
        gender: null,
        dateOfBirth: null,
        role: null,
      },
    };
  },
  template: `
    <div class="profile-container">
    <div class="profile-header">
      <h2>Welcome, {{ user.firstName }} {{ user.lastName }}!</h2>
  <!--<img :src="user.photoUrl" alt="User Photo" class="profile-photo"> -->
    </div>

    <div class="user-info">
      <p><strong>Username:</strong> {{ user.username }}</p>
      <p><strong>First Name:</strong> {{ user.firstName }}</p>
      <p><strong>Last Name:</strong> {{ user.lastName }}</p>
      <p><strong>Gender:</strong> {{ user.gender }}</p>
      <p><strong>Date of Birth:</strong> {{ user.dateOfBirth }}</p>
      <p><strong>Role:</strong> {{ user.role }}</p>
    </div>

    <div class="button-container">
      <button @click="editUser">Edit Profile</button>
      <button @click="viewRentals">View Rentals</button>
    </div>
  </div>
  `,
  mounted() {
    axios.get("rest/users/getUser/" + this.username).then(response => (this.user = response.data));
  },
    
  methods:{
	  editUser: function(){
		  this.$router.push(`/` + this.username + `/edit`);
	  },
	  viewRentals: function() {
		  this.$router.push(`/` + this.username + '/rentals');
	  }
  }
  
});