window.onload = function (){
  updateZJ()//更新总计
  //当页面加载完成时 我们需要绑定各种事件
  //根据id获取到表格
  var fruitTbl =document.getElementById("tbl_fruit");
  var rows = fruitTbl.rows;
  for (var i = 1; i <rows.length-1 ; i++) {
    trBindEvent(rows[i])
  }
  $("addBtn").onclick=addFruit;
}

function trBindEvent(tr){
  //1.绑定鼠标悬浮设置背景颜色事件
  tr.onmouseover=showBGColor;   //没有小括号，不要返回值
  //鼠标离开
  tr.onmouseout=clearBGColor;
  //获取tr这一行的所有单元格
  var cells = tr.cells;
  var td = cells[1];
  //2.绑定鼠标悬浮事件设置单元格变手势的事件
  td.onmouseover=showHand;
  //3.绑定鼠标点击单元格修改单价的事件
  td.onclick=editPrice;
  //7.绑定删除小图标的点击事件
  var delImg =cells[4].firstChild;
  if (delImg && delImg.tagName=="IMG"){
    delImg.onclick=delFruit;
  }
}
function $(id){
  return document.getElementById(id);
}

//添加一行
function addFruit(){
  var Tbl =$("tbl2");
  var rows = Tbl.rows;


  var input1 = $("name");
  var input2 = $("price");
  var input3 = $("count");

  var fruitTbl =$("tbl_fruit");
  var rs = fruitTbl.rows;
  //添加行
  var trr = fruitTbl.insertRow(rs.length-1);

  //添加TD
   var newTd1=trr.insertCell(0);
   var newTd2=trr.insertCell(1);
   var newTd3=trr.insertCell(2);
   var newTd4=trr.insertCell(3);
   var newTd5=trr.insertCell(4);

   newTd1.innerText=input1.value;
   newTd2.innerText=input2.value;
   newTd3.innerText=input3.value;
   newTd4.innerText=parseInt(input2.value)*parseInt(input3.value);
   newTd5.innerHTML="<img src=\"删除.jpg\" class=\"imgdel\"/>";
    updateZJ();
    trBindEvent(trr);
}

//删除一行
function delFruit(){
  if (event && event.srcElement && event.srcElement.tagName=="IMG"){
    //alert 表示弹出一个只有确定按钮的对话框
    //confirm表示弹出一个有确定和取消按钮的对话框
    if (window.confirm('是否删除这一行')){
      var img = event.srcElement;
      var tr = img.parentElement.parentElement;
      var fruitTbl =document.getElementById("tbl_fruit");
      fruitTbl.deleteRow(tr.rowIndex);
    }
    updateZJ();
  }
}

//当鼠标悬浮时 有背景颜色
function showBGColor(){
  //当前发生的事件
  //event.srcElement 事件源
  // alert(event.srcElement);
  // alert(event.srcElement.tagName);
  if (event && event.srcElement && event.srcElement.tagName=="TD"){
    var td = event.srcElement;
    //td.parentElement 表示获取TD的父元素
    var tr = td.parentElement;

    //如果想要通过JS代码设置某节点的样式 则需要加上style
    tr.style.backgroundColor="red";

    //tr.cells表示获取这个tr中所有的单元格
    var tds=tr.cells;
    for (var i = 0; i <tds.length ; i++) {
      tds[i].style.color="white";
    }
  }
}

//当鼠标离开时 恢复原样
function clearBGColor(){
  if (event && event.srcElement && event.srcElement.tagName=="TD"){
    var td = event.srcElement;
    var tr = td.parentElement;
    tr.style.backgroundColor="transparent";
    var tds=tr.cells;
    for (var i = 0; i <tds.length ; i++) {
      tds[i].style.color="black";
    }
  }
}

//当鼠标悬浮在单元格时，显示手势
function showHand(){
  if (event && event.srcElement && event.srcElement.tagName=="TD"){
    var td = event.srcElement;
    //cursor 光标
    td.style.cursor="hand";
  }
}

//当鼠标点击单元格时可以修改单价
function editPrice(){
  if (event && event.srcElement && event.srcElement.tagName=="TD"){
    var priceTD = event.srcElement;
    //判断当前priceTD有子节点，且第一个子节点的类型是文本类型  TEXTNode对应的是3，ElementNode对应的是1
    if (priceTD.firstChild && priceTD.firstChild.nodeType==3){
      //innerTEXT  表示设置或者获取当前节点的内部文本
      var oldPrice= priceTD.innerText;
      //innerHTML  表示设置当前节点的内部HTML
      priceTD.innerHTML="<input type='text' size='4'/>";
      var input = priceTD.firstChild;
      if (input.tagName='INPUT'){
        input.value=oldPrice;
        //选中输入框内部的文本
        input.select();
        //4.绑定输入框失去焦点事件，失去焦点，更新单价
        input.onblur=updatePrice;

        //8.在输入框上绑定键盘按下的事件，保证用户输入的是数字
        input.onkeydown=ckInput;
      }
    }
  }
}

//检验键盘按下的值的方法
function ckInput(){
  var kc =event.keyCode;
  //0-9 :48-57  backSpace：8  enter回车：13
  if (!((kc>=48&&kc<=57)||kc==8||kc==13)){
      event.returnValue=false;
  }
  if (kc==13){
    event.srcElement.blur();
  }
}

function updatePrice(){
  if (event && event.srcElement && event.srcElement.tagName=="INPUT"){
    var input =event.srcElement;
    var newPrice = input.value;
    //当前input节点的父节点时TD
    var priceTD= input.parentElement;
    priceTD.innerText=newPrice;

    //5.更新当前行的小计这一格子的值
    updateXJ(priceTD.parentElement);
  }
}

function updateXJ(tr){
  if (tr && tr.tagName=="TR"){
    var tds = tr.cells;
    var price = tds[1].innerText;
    var count = tds[2].innerText;
    tds[3].innerText= parseInt(price)*parseInt(count);
    //6.更新总计
    updateZJ();
  }
}

//更新总计
function updateZJ(){
  var fruitTbl =document.getElementById("tbl_fruit");
  var rows = fruitTbl.rows;
  var sum = 0;
  for (var i = 1; i <rows.length-1 ; i++) {
    var tr = rows[i];
    var xj = parseInt(tr.cells[3].innerText);
    sum=sum+xj;
  }
  rows[rows.length-1].cells[1].innerText=sum;
}
