set @sOutput = 0;
call c10_qlykhachhang.spSaveOrderWithParameter('{"name":"Thu Thuy125","phone":"+1 (318) 939-6315","address":"Ut ex nostrud id lab","total":8000.0,"orderItems":[{"idProduct":2,"quantiy":2},{"idProduct":3,"quantiy":2}]}', @sOutput);
select @sOutput;
