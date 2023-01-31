/**
	in 
    out
    inout
**/
DROP PROCEDURE IF EXISTS sp_TinhLuongTBBoMon;
delimiter //
create procedure sp_TinhLuongTBBoMon(
	in pBoMon varchar(10),
    out pLuong decimal(10,1)
)
begin
	set pLuong =  
			( select avg(LUONG) as luong
			from giaovien
			where MABM  = pBoMon
			group by MABM );
end //;


DROP PROCEDURE IF EXISTS sp_TinhLuongTBBoMon_Cach1;
delimiter //
create procedure sp_TinhLuongTBBoMon_Cach1(
	in pBoMon varchar(10)
)
begin
		select *
		from giaovien
		where MABM  = pBoMon
		group by MABM;
end //



# Câu 1: Hoàn thiện thêm giáo viên
#  + Truyền vào: HOTEN,LUONG,PHAI,NGSINH,GVQLCM, MABM
#  + Không cần truyền mã giáo viên, tự động sinh mã giáo viên mới và thêm vào.
#  Thêm thành công thì trả ra MAGV mới, không thêm được thì trả MAGV mới = NULL. Đổi lại dùng tham số INOUT
#  + Kiểm tra tồn tại MABM trước khi thêm

drop procedure if exists sp_ThemGiaoVien;
delimiter //
create procedure sp_ThemGiaoVien(
	inout pMAGV varchar(3),
	in pHoTen varchar(40),
    in pLuong decimal(10,1),
    in pPhai varchar(3),
    in pNgaySinh date,
    in pDiaChi varchar(50),
    in pGVQLCM varchar(3),
    in pMaBM varchar(4),
    out pMessage varchar(50)
)
begin
	declare flag boolean;
    declare id varchar(3);
    set flag = true;
    set pMessage = '';
	if(not exists (SELECT * FROM giaovien where MAGV = pGVQLCM)) then
		set flag = false;
        set pMessage = concat(pMessage, 'Mã giáo viên không hợp lệ \n');
    end if;
    if(not exists (SELECT * FROM bomon where MABM = pMABM)) then
		set flag = false;
        set pMessage = concat(pMessage, 'Mã bộ môn không hợp lệ');
    end if;
    
    if(flag = true) then
		set @maxID = (SELECT MAGV
						FROM giaovien
						order by MAGV desc limit 0, 1);
		-- @maxID: '001', '002', '010'
        set @max = cast(@maxID as decimal) + 1;
        if(@max <10) then
			set id = concat('00', @max);
		else
			if(@max <100) then
				set id = concat('0', @max);
			end if;
		end if;
        
        INSERT INTO `giaovien` (`MAGV`, `HOTEN`, `LUONG`, `PHAI`, `NGSINH`, `DIACHI`, `GVQLCM`, `MABM`) 
        VALUES (id, pHoTen, pLuong, pPhai, pNgaySinh, pDiaChi, pGVQLCM, pMaBM);
		set pMAGV = id;
    end if;
    
    
end //

-- Xuất ra thông tin của giáo viên (MAGV, HOTEN) và mức lương của giáo viên. 
-- Mức lương được xếp theo,quy tắc: Lương của giáo viên < $1800 : “THẤP” ; 
-- Từ $1800 đến $2200: TRUNG BÌNH; Lương > $2200:“CAO”
select gv.HOTEN, gv.LUONG, if(gv.LUONG < 1800, "THAP", if(gv.LUONG > 2200, "CAO", "TRUNGBINH")) as MUCLUONG
from giaovien gv;

create view v_LuongTBGiaoVien
as SELECT * 
FROM giaovien gv
where luong >= (SELECT avg(LUONG)
				FROM giaovien gv1
				where gv1.MABM = gv.MABM);
                
create view v_GiaoVienMini
as 
SELECT gv.MAGV, gv.LUONG
FROM giaovien gv



