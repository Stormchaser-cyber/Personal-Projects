# scores.py file
#
# Created -- Ted Strombeck -- August 30, 2023
# Last Updated -- August 30th, 2023
# Version 1.0.0
#

def calculate_pe_score(pe_stat):
	"""
	calculates a pe score on a scale from 1 - 100 with 100 being the top
	"""
	score = 1.0

	if pe_stat > 0: score = pe_stat
	if pe_stat > 0 and pe_stat <= 25: score = (pe_stat * 4)

	return score

def calculate_eps_score(eps_stat):
	"""
	calculates an eps score on a scale from 1 - 100 with 100 being the top
	"""
	score = 1.0

	score = eps_stat

	return score

def calculate_pb_score(current_pb, min_pb=0, med_pb=0, max_pb=0):
	"""
	calculates a pb score on a scale from 1 - 100 with 100 being the top
	"""
	score = 1.0

	if current_pb <= 2 and current_pb >= 1: score = (current_pb * 50)
	return score
		
def calculate_dividend_yield_score(current_dividend_yield, min_dividend_yield=0, med_dividend_yield=0, max_dividend_yield=0): 
	"""
	calculates a dividiend_yield_score score on a scale from 1 - 100 with 100 being the top
	"""
	score = 1.0

	if current_dividend_yield <= 5 and current_dividend_yield >= 1: score = (current_dividend_yield * 20)
	return score

def calculate_ebitda_growth_rate_score(current_yoy_ebitda_growth_rate): 
	"""
	calculates a growth_rate_score score on a scale from 1 - 100 with 100 being the top
	"""
	score = 1.0

	if current_yoy_ebitda_growth_rate <= 60: score = (current_yoy_ebitda_growth_rate * 1.6666)
	return score

def calculate_debt_to_equity_score(current_d2e, min_d2e=0, med_d2e=0, max_d2e=0): 
	"""
	calculates a debt_to_equity score on a scale from 1 - 100 with 100 being the top
	"""
	score = 1.0

	if current_d2e > 0: score = 100-current_d2e
	return score

def calculate_roe_score(current_roe, min_roe=0, med_roe=0, max_roe=0): 
	"""
	calculates a roe score on a scale from 1 - 100 with 100 being the top
	"""
	score = 1.0

	if current_roe <= 20 and current_roe >= 15: score = (current_roe * 5)
	return score

def calculate_operating_margin_score(current_operating_margin_percentage, min_operating_margin_percentage=0, med_operating_margin_percentage=0, max_operating_margin_percentage=0): 
	"""
	calculates an operating_margin score on a scale from 1 - 100 with 100 being the top
	"""
	score = 1.0

	if current_operating_margin_percentage >= 15: score = (current_operating_margin_percentage * 4)
	else: score = current_operating_margin_percentage
	return score

def calculate_fcf_score(current_fcf_margin_percentage): 
	"""
	calculates an fcf score on a scale from 1 - 100 with 100 being the top
	"""
	score = 1.0

	if current_fcf_margin_percentage <= 25 and current_fcf_margin_percentage > 0: score = (current_fcf_margin_percentage * 4)
	return score

if __name__ == '__main__':
	print(calculate_fcf_score(0))
	print(float(18.256))
	print(calculate_fcf_score(24.7514))
	print(calculate_fcf_score(14.58))