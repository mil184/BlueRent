Vue.component("rentACarPage",{
	data() {
		return {
			rentACar : {
				name : this.$route.params.name,
				startTime : null,
				endTime : null,
				status : null,
				locationId : null,
				logoPath : null,
				grade : null
			}
		};
	},
	
	template :
	`
		<div class="create-container">
	    	
	    		<img :src="rentACar.logoPath" alt="logo" class="logo">
        		<h3>{{ rentACar.name }}</h3>
        		
        		<br>
        		
        		<p>Working hours: {{ rentACar.startTime }} - {{ rentACar.endTime }}</p>
        		<p>Location: </p>
        		<p>Grade: {{ rentACar.grade }}</p>
        		
	    	
		</div>
	`,
	
	mounted() {
		axios.get("rest/rentACars/getRentACar/" + this.rentACar.name).then(response => (this.rentACar = response.data));
		console.log(this.rentACar);
	},
	
	
});











