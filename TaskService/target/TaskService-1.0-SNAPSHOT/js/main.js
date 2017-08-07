/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var rootURL = "http://localhost:8080/TaskService/webresources/tasks";

function getTasks() {

     $.ajax({
         type: "GET",
         url: rootURL,
         contentType: "application/json; charset=utf-8",
         dataType: "json",
         success: function (data, status, jqXHR) {
             alert(data);
         },

         error: function (jqXHR, status) {
             alert("deu zica");
         }
    });

}
// Register listeners
('#btnTeste').click(function() {
	//search($('#searchKey').val());
        getTasks();
	return false;
});




