Vue.component("registeredUsers", {
  data() {
    return {
      allUsers: null,
      filteredUsers: null,
      searchQueryFirstName: "",
      searchQueryLastName: "",
      searchQueryUsername: "",
      sortFirstName: true,
      sortLastName: true,
      sortUsername: true,
      selectedRole: "",
      selectedUserType: "",
    };
  },

  template: `
   <div class="registered-users-container">
    <h2>Registered Users</h2>

    <div class="search-bar">
      <input type="text" v-model="searchQueryFirstName" @input="filterUsers" placeholder="Search by first name">
      <input type="text" v-model="searchQueryLastName" @input="filterUsers" placeholder="Search by last name">
      <input type="text" v-model="searchQueryUsername" @input="filterUsers" placeholder="Search by username">
    </div>

    <div class="sort-buttons">
      <button @click="sortBy('firstName')" class="sort-button">Sort by first name</button>
      <button @click="sortBy('lastName')" class="sort-button">Sort by last name</button>
      <button @click="sortBy('username')" class="sort-button">Sort by username</button>
    </div>

    <div class="filter-options">
      <label for="role">Role:</label>
      <select v-model="selectedRole" @change="filterUsers">
        <option value="">All roles</option>
        <option value="Admin">Admin</option>
        <option value="Manager">Manager</option>
        <option value="Customer">Customer</option>
      </select>

      <label for="userType">User Type:</label>
      <select v-model="selectedUserType" @change="filterUsers">
        <option value="">All types</option>
        <option value="Gold">Gold</option>
        <option value="Silver">Silver</option>
        <option value="Bronze">Bronze</option>
      </select>
    </div>
    
    <table class="user-table">
      <thead>
         <tr>
           <th>First Name</th>
           <th>Last Name</th>
           <th>Username</th>
           <th>Gender</th>
           <th>Date Of Birth</th>
           <th>Role</th>
           <th>User Type</th>
         </tr>
      </thead>
      <tbody>
         <tr v-for="user in filteredUsers" :key="user.username">
           <td>{{ user.firstName }}</td>
           <td>{{ user.lastName }}</td>
           <td>{{ user.username }}</td>
           <td>{{ user.gender }}</td>
           <td>{{ user.dateOfBirth }}</td>
           <td>{{ user.role }}</td>
           <td>{{ user.type }}</td>
         </tr>
      </tbody>
    </table>
  </div>
  `,

  mounted() {
    axios.get('rest/users/getRegistered').then(response => {
      this.allUsers = response.data;
      this.filteredUsers = this.allUsers.slice();
    });
  },

  methods: {
    filterUsers() {
      this.filteredUsers = this.allUsers.filter(user => {
        const firstNameMatches = user.firstName.toLowerCase().includes(this.searchQueryFirstName.toLowerCase());
        const lastNameMatches = user.lastName.toLowerCase().includes(this.searchQueryLastName.toLowerCase());
        const usernameMatches = user.username.toLowerCase().includes(this.searchQueryUsername.toLowerCase());
        const roleMatches = !this.selectedRole || user.role === this.selectedRole;
        const userTypeMatches = !this.selectedUserType || user.type === this.selectedUserType;

        return firstNameMatches && lastNameMatches && usernameMatches && roleMatches && userTypeMatches;
      });

      this.applyFilters();
    },

    sortBy(field) {
      this.filteredUsers = this.filteredUsers.sort((a, b) => {
        const aValue = this.resolveFieldValue(a, field);
        const bValue = this.resolveFieldValue(b, field);

        return this[field] ? aValue.localeCompare(bValue) : bValue.localeCompare(aValue);
      });

      this[field] = !this[field];
    },

    applyFilters() {
      this.sortFilteredUsers();
    },

    sortFilteredUsers() {
    },

    resolveFieldValue(user, field) {

      switch (field) {
        case 'firstName':
          return user.firstName.toLowerCase();
        case 'lastName':
          return user.lastName.toLowerCase();
        case 'username':
          return user.username.toLowerCase();
        default:
          return '';
      }
    },
  },
});
