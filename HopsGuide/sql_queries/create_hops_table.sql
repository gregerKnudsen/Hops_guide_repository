CREATE TABLE IF NOT EXISTS Hops (
	   	_id VARCHAR(200) PRIMARY KEY,
	   	country VARCHAR(200),
	   	alpha FLOAT,
	   	beta FLOAT,
	   	type VARCHAR(200),
	   	storageIndex INTEGER,
	   	typicalFor VARCHAR(200),
	   	substitutes VARCHAR(500),
	   	aroma VARCHAR(500),
	   	information VARCHAR(720))