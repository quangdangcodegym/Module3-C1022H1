-- Count *: dem tat ca cac dong, count cot: dem tren cot ma khac null
SELECT count(GVQLCM)
FROM c10_quanlydetai.giaovien;


-- với mỗi giáo viên, cho biết MAGV và số luong công việc mà giáo viên đó tham gia
select gv.MAGV, count(tgdt.MADT) SLCVTG
from thamgiadt tgdt right join giaovien gv on gv.MAGV = tgdt.MAGV
group by MAGV;

-- cho biết bộ môn có 2 giáo viên trở lên
select gv.MABM
from giaovien gv
group by gv.MABM
having count(gv.MABM) >= 2;

-- cho biết bộ môn có 2 giáo viên trở lên
select gv.MABM
from giaovien gv
where gv.PHAI = 'NAM'
group by gv.MABM
having count(gv.MABM) >= 2;

-- cho biết những giáo viên và số lượng đề tài của những GV tham gia từ 2 đề tài trở lên
-- distint không trùng lặp
select *
from giaovien gv join thamgiadt tgdt on gv.MAGV = tgdt.MAGV
group by tgdt.MAGV
having count(distinct tgdt.MADT) >=2 ;

-- cho biết những giáo viên không có người thân nào
select *
from giaovien gv
where gv.MAGV not in ( 
		select nt.MAGV
		from nguoithan nt);
        
-- cách 2:
select *
from giaovien gv left join nguoithan nt on gv.MAGV = nt.MAGV
where nt.MAGV is null;

-- cách 3: dùng not exist
select *
from giaovien gv
where not exists (select * from nguoithan nt where nt.MAGV = gv.MAGV);

-- Cho biết những giao viên có lương nhỏ nhất
select *
from giaovien
where LUONG = (SELECT min(LUONG) FROM c10_quanlydetai.giaovien);

select *
from giaovien
where LUONG <= all (SELECT LUONG FROM c10_quanlydetai.giaovien);


-- Cho biết những giáo viên có lương lớn hơn mức lương trung bình của giáo viên bộ môn
select *, (select avg(gv1.LUONG) from giaovien gv1 where gv.MABM = gv1.MABM group by gv1.MABM) as LTB
from giaovien gv
where gv.LUONG > (select avg(gv1.LUONG) from giaovien gv1 where gv.MABM = gv1.MABM group by gv1.MABM);


select *
from giaovien gv join (select gv1.MABM, avg(gv1.LUONG) as ltb_bm from giaovien gv1 group by gv1.MABM) as ltb on ltb.MABM = gv.MABM
where gv.LUONG > ltb.ltb_bm




