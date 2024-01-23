Vue.component("home", {
  data() {
    return {
      rentACars: null,
      locations: {}, // Mapping of locationId to location information
      sortName: true,
      sortGrade : true,
      searchQueryName: "",
      searchQueryGrade: "",
      searchQueryLocation: "",
    };
  },

  template: `
    <div id="app">
      <h2>Rent a Cars</h2>

      <!-- Search input -->
      <input type="text" v-model="searchQueryName" @input="searchByName" placeholder="Search by name">
      <input type="text" v-model="searchQueryGrade" @input="searchByGrade" placeholder="Search by grade">
      <input type="text" v-model="searchQueryGrade" @input="searchByLocation" placeholder="Search by location">

	  <br>

      <!-- Sort button -->
      <button @click="sortBy('name')" class="sort-button">Sort by Name</button>
      <button @click="sortByGrade('grade')" class="sort-button">Sort by Grade</button>

      <div v-for="rentACar in filteredRentACars" class="list-item">
        <img :src="rentACar.logoPath" alt="logo" class="logo">
        <h3>{{ rentACar.name }}</h3>
        <p>Working hours: {{ rentACar.startTime }} - {{ rentACar.endTime }}</p>
        <p>Location: {{ getLocationInfo(rentACar.locationId) }}</p>
        <p>Grade: {{ rentACar.grade }}</p>
      </div>
    </div>
  `,

  mounted() {
    // Fetch rentACars data
    axios.get("rest/rentACars/getAll").then(response => {
      this.rentACars = response.data;
      // Fetch location information and create the locations mapping
      axios.get("rest/locations/getAll").then(locationsResponse => {
        this.locations = locationsResponse.data.reduce((map, location) => {
          map[location.locationId] = `${location.address}, ${location.city}`;
          return map;
        }, {});
        
        console.log("Locations Mapping:", this.locations); // Debugging output
        
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
      
      return this.rentACars;
    },
  },

  methods: {
    getLocationInfo(locationId) {
      return this.locations[locationId] || "Novosadska 1, Novi Sad";
    },

    searchByName() {
      // Handle search by name
    },

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
  },
});
