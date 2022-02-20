export enum TypeClient{
    FISICA, JURIDICA
}

export interface Company {
    typeClient: TypeClient,
    name: string,
    fantasyName:string,
    cnpj: string,
    cep: string,
    address: string,
    district: string,
    city: string,
    phone: string,
    email: string 
}
