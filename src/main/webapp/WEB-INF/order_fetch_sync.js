let orders;	

function $id(id) {
  return document.getElementById(id);
} 

//---------------------------------------------------clearForm()
function clearForm(){
  let inputs = document.querySelectorAll("#orderPanel input");
  for(let i in inputs){ //清空所有輸入盒
    inputs[i].value = "";
  }
  $id("JOB").selectedIndex = 0;  //將下拉式選單reset在第0個位置
}
//----------------------------------------------------------
function showOrders(){
	let html = "<option value='0'>全部</option>";
	for (let i=0; i<depts.length; i++) {
		html += `<option value='${depts[i].DEPTNO}'>${depts[i].DNAME}</option>`;
	}
	document.getElementById("deptnoOptions").innerHTML = html;
}

//----------------------------------------------------------
async function getOrders(deptno=0){
let response1 = await fetch("http://localhost:8080/BuyGo/ShowOrdersServlet");
	orders = await response1.json();
  showOrders();//將其顯示到頁面中

}

//----------------------------------------------------------

window.addEventListener("load", function () {
	

})