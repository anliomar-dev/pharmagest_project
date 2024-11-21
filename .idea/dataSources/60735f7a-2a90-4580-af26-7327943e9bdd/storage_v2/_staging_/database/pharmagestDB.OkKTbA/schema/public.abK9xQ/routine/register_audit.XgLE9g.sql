create function register_audit() returns trigger
    language plpgsql
as
$$
BEGIN
    INSERT INTO audit (nom_fournisseur, date_heure_enregistrement)
    VALUES (NEW.nom, CURRENT_TIMESTAMP);
    RETURN NEW;
END;
$$;

alter function register_audit() owner to postgres;

