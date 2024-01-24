const LogIn = {template: "<log-in></log-in>"}
const Register = {template: "<register></register>"}
const UserPage = {template: "<userPage></userPage>"}
const EditUser = {template: "<editUser></editUser>"}
const CreateRentACar = { template : "<create-rentACar></create-rentACar>" }
const HomePage = { template : "<home></home>" }
const RentACarPage = { template : "<rentACarPage></rentACarPage>"}
const CreateComment = { template : "<create-comment></create-comment>"}
const AdminPage = {template: "<adminPage></adminPage>"}
const RegisteredUsers = {template: "<registeredUsers></registeredUsers>"}
const ManagerPage = {template: "<managerPage></managerPage>"}
const CustomerPage = {template: "<customerPage></customerPage>"}
const CustomerRentals = {template: "<customerRentals></customerRentals>"}

const router = new VueRouter({
	mode: 'hash',  
	routes: [
		{path : "/login", component: LogIn},
		{path : "/register", component: Register},
		{path : "/registeredUsers", component: RegisteredUsers},
		{ path : "/home", component : HomePage },
		{ path : "/rentACars/create", component: CreateRentACar},
		{ path : "/rentACars/:name", component: RentACarPage},
		{ path : "/createComment", component: CreateComment},
        {path : "/:username", component: UserPage},
        {path : "/:username/edit", component: EditUser},
        {path : "/:username", component: UserPage},
        {path : "/:username/edit", component: EditUser},
        {path : "/:username/adminPage", component: AdminPage},
        {path : "/:username/managerPage", component: ManagerPage},
        {path : "/:username/customerPage", component: CustomerPage},
        {path : "/:username/rentals", component: CustomerRentals}
        ]
	
});

var app = new Vue({
	router,
	el: "#mainPage"
});