
alter table t_loan_apply add cycle number(12,2);
alter table t_loan_apply add unit varchar2(2);
alter table t_loan_apply add loan_Interest number(16,2);
alter table t_loan_apply add total_Repay_Amount number(24,8);