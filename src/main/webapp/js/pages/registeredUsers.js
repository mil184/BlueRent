Vue.component("registeredUsers", {
  data() {
    return {
      allUsers: [],
      filteredUsers: [],
      searchQuery: "",
      selectedRole: "",
      selectedUserType: "",
      sortBy: "username",
      sortDirection: "asc",
    };
  },
  template: `
    <div class="registered-users-container">
    <h2>Lista svih registrovanih korisnika</h2>

    <!-- Pretraga -->
    <div class="search-bar">
      <input v-model="searchQuery" @input="filterUsers" placeholder="Pretraži po imenu, prezimenu ili korisničkom imenu" />
    </div>

    <!-- Filtriranje -->
    <div class="filter-options">
      <label for="role">Uloga:</label>
      <select v-model="selectedRole" @change="filterUsers">
        <option value="">Sve uloge</option>
        <option value="Admin">Admin</option>
        <option value="Kupac">Kupac</option>
        <!-- Dodajte ostale uloge prema potrebi -->
      </select>

      <label for="userType">Tip korisnika:</label>
      <select v-model="selectedUserType" @change="filterUsers">
        <option value="">Svi tipovi</option>
        <option value="Regular">Regular</option>
        <option value="Premium">Premium</option>
        <!-- Dodajte ostale tipove korisnika prema potrebi -->
      </select>
    </div>

    <!-- Prikaz korisnika -->
    <ul class="user-list">
      <li v-for="user in filteredUsers" :key="user.username" class="user-item">
        <p><strong>Ime:</strong> {{ user.firstName }}</p>
        <p><strong>Prezime:</strong> {{ user.lastName }}</p>
        <p><strong>Korisničko ime:</strong> {{ user.username }}</p>
        <!-- Dodajte ostale informacije o korisniku koje želite prikazati -->
      </li>
    </ul>
  </div>
  `,
  mounted() {
	 axios
    .get('rest/users/getRegistered')
    .then(response => {
      console.log('Dohvaćeni podaci:', response.data);
      this.allUsers = response.data;
    })
    .catch(error => console.error('Greška pri dohvatanju podataka:', error));

    // Inicijalno postavljamo prikazane korisnike na sve korisnike
    this.filteredUsers = this.allUsers;
  },
  methods: {
    filterUsers() {
      // Funkcija za filtriranje korisnika na osnovu unetih vrednosti
      this.filteredUsers = this.allUsers.filter(user => {
        return (
          user.firstName.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
          user.lastName.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
          user.username.toLowerCase().includes(this.searchQuery.toLowerCase())
        );
      });

      if (this.selectedRole) {
        this.filteredUsers = this.filteredUsers.filter(user => user.role === this.selectedRole);
      }

      if (this.selectedUserType) {
        this.filteredUsers = this.filteredUsers.filter(user => user.type === this.selectedUserType);
      }

      // Sortiranje rezultata
      this.sortFilteredUsers();
    },
    sortFilteredUsers() {
      // Funkcija za sortiranje prikazanih korisnika
      this.filteredUsers = this.filteredUsers.sort((a, b) => {
        const aValue = a[this.sortBy];
        const bValue = b[this.sortBy];

        if (this.sortDirection === "asc") {
          return aValue.localeCompare(bValue);
        } else {
          return bValue.localeCompare(aValue);
        }
      });
    },
  },
  
});