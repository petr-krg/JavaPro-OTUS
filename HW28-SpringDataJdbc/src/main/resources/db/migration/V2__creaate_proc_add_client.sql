CREATE OR REPLACE PROCEDURE add_client(
    cl_name VARCHAR(50),
    cl_address VARCHAR(124),
    cl_phone VARCHAR(50)
) LANGUAGE plpgsql AS $$
DECLARE CurrentClID BIGINT;
BEGIN
    INSERT INTO client(name) VALUES(cl_name) RETURNING id INTO CurrentClID;
    INSERT INTO address(address, client_id) VALUES(cl_address, CurrentClID);
    INSERT INTO phone(number, client_id) VALUES(cl_phone, CurrentClID);
END;
$$;



