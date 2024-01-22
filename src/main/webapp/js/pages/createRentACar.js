Vue.component("create-rentACar",
{
	data : function()
	{
		return {
			rentACar : {
				name : null,
				startTime : null,
				endTime : null,
				status : null,
				locationId : null,
				logoPath : null,
				grade : null
			},
			
			errorMessages : {
				name : " ",
				startTime : " ",
				endTime : " ",
				status : " ",
				locationId : " ",
				logoPath : " ",
				grade : " "
			}
		}
	},
	
	template :
	`
		<div class="create-container">
	    	<h2>CREATE RENT A CAR OBJECT</h2>
	    	<form class="login-form" @submit="TryCreate">
	    		
	    		<p>Select name:</p>
	        	<small>{{ errorMessages.name }}</small>
	        	<input type="text" placeholder="Name" v-model="rentACar.name">
	        	
	        	<p>Select working hours:</p>	        	
				<small>{{ errorMessages.startTime }}</small>
	        	<input type="time" placeholder="Start time" v-model="rentACar.startTime">	        	
	        	<small>{{ errorMessages.endTime }}</small>
	        	<input type="time" placeholder="End time" v-model="rentACar.endTime">
	        	
	        	<p>Select location:</p>
	        	<small>{{ errorMessages.locationId }}</small>
	        	<input type="text" placeholder="Location Id" v-model="rentACar.locationId">
	        	
	        	<p>Select logo:</p>
	        	<small>{{ errorMessages.logoPath }}</small>
	        	<input type="text" placeholder="Logo path" v-model="rentACar.logoPath">
	  
	      <input type="submit" value="Create">
	    </form>
	  </div>
	`,
		
	methods :
	{
		TryCreate() {
			let valid = true;
      		this.errorMessages = {};
      		
      		if (!this.rentACar.name) {
	        	valid = false;
	        	this.errorMessages.name = "Please enter name.";
      		}
      		
      		if (!this.rentACar.startTime) {
	        	valid = false;
	        	this.errorMessages.startTime = "Please select start time.";
      		}
      		
      		if (!this.rentACar.endTime) {
	        	valid = false;
	        	this.errorMessages.endTime = "Please select end time.";
      		}
      		
      		if (!this.rentACar.locationId) {
	        	valid = false;
	        	this.errorMessages.locationId = "Please select a location.";
      		}
      		
      		if (!this.rentACar.logoPath) {
	        	valid = false;
	        	this.errorMessages.logoPath = "Please select a location.";
      		}
      		
      		if (valid){
				  axios.post('rest/rentACars/create',
				  {
					  "name" : this.rentACar.name,
					  "startTime" : this.rentACar.startTime,
					  "endTime" : this.rentACar.startTime,
					  "status" : 'OPEN',
					  "locationId" : this.rentACar.locationId,
					  "logoPath" : this.rentACar.logoPath,
					  "grade" : 0
					  
				  }).catch(error => 
		    			console.error(error));
			 }
		}
	}
});







