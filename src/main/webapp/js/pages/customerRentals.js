Vue.component("customerRentals", {
 data() {
  return {
    allRentals: null,
    filteredRentals: null,
    minPrice: null,
    maxPrice: null,
    minRentalDate: null,
    maxRentalDate: null,
    sortPrice: true,
    sortStartDate: true,
    sortEndDate: true,
    username: null
  };
},

template: `
 <div class="customer-rentals-container">
    <h2>Rentals</h2>

    <div class="search-bar">
      <input type="number" v-model="minPrice" @input="filterRentals" placeholder="Min price">
      <input type="number" v-model="maxPrice" @input="filterRentals" placeholder="Max price">
      <input type="date" v-model="minRentalDate" @input="filterRentals" placeholder="Min rental date">
      <input type="date" v-model="maxRentalDate" @input="filterRentals" placeholder="Max rental date">
    </div>

    <div class="sort-buttons">
      <button @click="sortBy('price')" class="sort-button">Sort by price</button>
      <button @click="sortBy('startDate')" class="sort-button">Sort by start date</button>
       <button @click="sortBy('endDate')" class="sort-button">Sort by end date</button>
    </div>

    <table class="rental-table">
      <thead>
        <tr>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Price</th>
          <th>Status</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="rental in filteredRentals" :key="rental.id">
          <td>{{ rental.startDate }}</td>
          <td>{{ rental.endDate }}</td>
          <td>{{ rental.price }}</td>
          <td>{{ rental.status }}</td>
        </tr>
      </tbody>
    </table>
 </div>
`,

mounted() {
	this.username = this.$route.params.username;
    axios.get('rest/rentals/getByCustomer/' + this.username).then(response => {
       this.allRentals = response.data;
       this.filteredRentals = this.allRentals.slice();
  });
},

methods: {
  filterRentals() {
    this.filteredRentals = this.allRentals.filter(rental => {
      const priceMatches = (this.minPrice === null || rental.price >= this.minPrice) &&
                           (this.maxPrice === null || rental.price <= this.maxPrice);

      const rentalDateMatches = (this.minRentalDate === null || rental.startDate >= this.minRentalDate) &&
                               (this.maxRentalDate === null || rental.endDate <= this.maxRentalDate);

      return priceMatches  && rentalDateMatches;
    });

    this.applyFilters();
  },

    sortBy(field) {
      this.filteredRentals = this.filteredRentals.sort((a, b) => {
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

   resolveFieldValue(rental, field) {
	   switch (field) {
		   case 'startDate':
			   return rental.startDate.toLowerCase();
           case 'endDate':
               return rental.endDate.toLowerCase();
           case 'price':
               return rental.price.toString().toLowerCase();
           default:
               return '';
       }
    },
  },
});