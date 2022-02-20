export enum TypeClient{
    FISICA, JURIDICA
}

export interface Person {
    id: number,
    typeClient: TypeClient,
    name: string,
    cpf: string,
    cep: string,
    address: string,
    district: string,
    city: string,
    phone: string,
    email: string 
}
