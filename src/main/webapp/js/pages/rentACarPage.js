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
		<div >
	    	
	    		<img :src="rentACar.logoPath" alt="logo" class="logo">
        		<h3>{{ rentACar.name }}</h3>
	    	
		</div>
	`,
	
	mounted() {
		axios.get("rest/rentACars/getRentACar/" + this.name).then(response => (this.rentACar = response.data));
		console.log(this.rentACar);
	},
	
	
});











