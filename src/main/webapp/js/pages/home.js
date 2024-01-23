Vue.component("home", {
  data() {
    return {
		rentACars: null,
		sortName: true,
		sortGrade : true,
		sortLocation : true,
		showOnlyOpenStatus: false,
	  	searchQueryName: "",
	  	searchQueryGrade: "",
	  	searchQueryLocation: ""
    };
  },

  template: `
    <div id="app">
      <h2>RENT A CAR OBJECTS</h2>

      <!-- Search input -->
      <input type="text" v-model="searchQueryName" placeholder="Search by name">
      <input type="text" v-model="searchQueryGrade" placeholder="Search by grade">
      <input type="text" v-model="searchQueryLocation" placeholder="Search by location">

	  <br>

      <!-- Sort button -->
      <button @click="sortBy('name')" class="sort-button">Sort by Name</button>
      <button @click="sortByGrade('grade')" class="sort-button">Sort by Grade</button>
      <button @click="sortByLocation('address')" class="sort-button">Sort by Location</button>
      <button @click="filterByOpenStatus" class="sort-button">Show only open</button>

      <div v-for="rentACar in filteredRentACars" class="list-item">
        <img :src="rentACar.logoPath" alt="logo" class="logo">
        <h3>{{ rentACar.name }}</h3>
        <p>Working hours: {{ rentACar.startTime }} - {{ rentACar.endTime }}</p>
        <p>Location: {{ rentACar.address }}, {{ rentACar.city }}</p>
        <p>Grade: {{ rentACar.grade }}</p>
      </div>
    </div>
  `,

  mounted() {
  // Fetch rentACars data
  axios.get("rest/rentACars/getAll").then(response => {
    this.rentACars = response.data;

    this.rentACars.sort((a, b) => {
      const statusA = a.status;
      const statusB = b.status;

      if (statusA === 'OPEN' && statusB !== 'OPEN') {
        return -1;
      } else if (statusA !== 'OPEN' && statusB === 'OPEN') {
        return 1;
      }

      // Default sorting for other fields (e.g., 'name')
      return this.sortName ? a.name.localeCompare(b.name) : b.name.localeCompare(a.name);
    });
  });
},


  computed: {
    filteredRentACars() {
      if (this.searchQueryName) {
        const query1 = this.searchQueryName.toLowerCase();
        return this.rentACars.filter(rentACar => rentACar.name.toLowerCase().includes(query1));
      }
      
      if (this.searchQueryGrade) {
        const queryGrade = parseFloat(this.searchQueryGrade);
        return this.rentACars.filter(rentACar => rentACar.grade === queryGrade);
      }
      
      if (this.searchQueryLocation) {
        const query1 = this.searchQueryLocation.toLowerCase();
        return (this.rentACars.filter(rentACar => rentACar.address.toLowerCase().includes(query1)) || 
        	this.rentACars.filter(rentACar => rentACar.city.toLowerCase().includes(query1)));
      }
      
      return this.rentACars;
    },
  },

  methods: {

    sortBy(field) {
      // Sorting logic
      this.rentACars.sort((a, b) => {
        const aValue = a[field].toLowerCase();
        const bValue = b[field].toLowerCase();

        return this.sortName ? aValue.localeCompare(bValue) : bValue.localeCompare(aValue);
      });

      // Toggle the sorting direction
      this.sortName = !this.sortName;
    },
    
    sortByLocation(address) {
      // Sorting logic
      this.rentACars.sort((a, b) => {
        const aValue = a[address].toLowerCase();
        const bValue = b[address].toLowerCase();

        return this.sortLocation ? aValue.localeCompare(bValue) : bValue.localeCompare(aValue);
      });

      // Toggle the sorting direction
      this.sortLocation = !this.sortLocation;
    },
    
    sortByGrade(number) {
      // Sorting logic
      this.rentACars.sort((a, b) => {
        const aValue = a[number];
        const bValue = b[number];

        return this.sortGrade ? aValue - bValue : bValue - aValue;
      });

      // Toggle the sorting direction
      this.sortGrade = !this.sortGrade;
    },
    
    filterByOpenStatus() {
	  // Toggle the showOnlyOpenStatus flag
	  this.showOnlyOpenStatus = !this.showOnlyOpenStatus;
	
	  // If showOnlyOpenStatus is true, reapply the filtering
	  if (this.showOnlyOpenStatus) {
	    this.rentACars = this.rentACars.filter(rentACar => rentACar.status === 'OPEN');
	  } else {
	    // If showOnlyOpenStatus is false, reset the original data
	    axios.get("rest/rentACars/getAll").then(response => {
	      this.rentACars = response.data;
	      // Sort by status and move 'OPEN' status to the top
	      this.rentACars.sort((a, b) => {
	        const statusA = a.status;
	        const statusB = b.status;
	
	        if (statusA === 'OPEN' && statusB !== 'OPEN') {
	          return -1;
	        } else if (statusA !== 'OPEN' && statusB === 'OPEN') {
	          return 1;
	        }
	
	        // Default sorting for other fields (e.g., 'name')
	        return this.sortName ? a.name.localeCompare(b.name) : b.name.localeCompare(a.name);
	      });
	    });
  }
},
  },
});
