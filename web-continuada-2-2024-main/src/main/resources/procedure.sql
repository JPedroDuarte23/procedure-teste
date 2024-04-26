CREATE PROCEDURE obter_todos_os_filmes()
LANGUAGE SQL
BEGIN
    SELECT *
    FROM Filmes;
END@