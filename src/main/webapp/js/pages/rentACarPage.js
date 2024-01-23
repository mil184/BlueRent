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
			},
	  	comments: []
		};
	},
	
	created() {
    axios.get('rest/comments/getAll')
      .then(response => {
        // Filter comments based on companyName
        this.comments = response.data.filter(comment => comment.companyName === this.rentACar.name);
      })
      .catch(error => console.error(error));
  },
	
	template :
	`
		<div>
			<div class="create-container">
		    	
	    		<img :src="rentACar.logoPath" alt="logo" class="logo">
	    		<h3>{{ rentACar.name }}</h3>
	    		
	    		<br>
	    		
	    		<p>Working hours: {{ rentACar.startTime }} - {{ rentACar.endTime }}</p>
	    		<p>Status: {{ rentACar.status }}</p>
	    		<p>Location: {{ rentACar.address }}, {{ rentACar.city }}</p>
	    		<p>Grade: {{ rentACar.grade }}</p>
	
			</div>
			
			<div class="comments">
	      		<h2>COMMENTS</h2>
	  			<div v-for="comment in comments">
	      			<p>{{comment.grade}}</p>
	      			<p>{{comment.text}}</p>
	  			</div>
	      	</div>
      	</div>
	`,
	
	mounted() {
		axios.get("rest/rentACars/getRentACar/" + this.rentACar.name)
			.then(response => (this.rentACar = response.data));
		console.log(this.rentACar);
	},
	
	methods : {
		
	}
});











