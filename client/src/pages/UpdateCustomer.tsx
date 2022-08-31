import React, {FC, useState} from 'react';
import {useTypedSelector} from "../hooks/useTypedSelector";
import Loading from "../components/Loading";
import Error from "../components/Error";
import {useActions} from "../hooks/useActions";
import {Customer} from "../types/customer";
import {Button, Form, Input, Select} from "antd";
import {rules} from "../utils/rools";
import {Option} from "antd/es/mentions";

const UpdateCustomer: FC = () => {
    const {customers, loading, error} = useTypedSelector(state => state.customer);

    const {updateCustomer, getCustomerById, getCustomers} = useActions();

    const [id, setId] = useState<number>(0);
    const [name, setName] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [age, setAge] = useState<number>(0);
    const [customer, setCustomer] = useState<Customer | null>(null);

    const handleCustomer = (e: React.ChangeEvent<HTMLSelectElement>) => setId(+e);
    const onNameChange = (e: React.ChangeEvent<HTMLInputElement>) => setName(e.target.value);
    const onEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => setEmail(e.target.value);
    const onAgeChange = (e: React.ChangeEvent<HTMLInputElement>) => setAge(+e.target.value);

    const fetchCustomer = async (e: React.MouseEvent<HTMLButtonElement>) => {
        const response = await getCustomerById(id);
        // @ts-ignore
        setCustomer(response);
    };

    const fetchUpdateCustomer = (e: React.MouseEvent<HTMLButtonElement>) => {
        updateCustomer(
            customer?.id,
            name || customer?.name,
            email || customer?.email,
            age || customer?.age,
            customer?.accounts || []
        );
        getCustomers();
    };

    if (loading) {
        return <Loading/>
    }

    if (error) {
        return <Error error={error}/>
    }

    return (
        <div className="container">
            <div className="title-wrapper">
                <h1>Update Customer</h1>
            </div>

            <Form onFinish={fetchCustomer} className={"form-data"}>
                <Form.Item
                    label="Choose customer"
                    name="customer"
                    rules={[rules.required('Please select customer!')]}
                >
                    <Select placeholder="Select customer to update"
                            style={{width: "100%"}} onChange={handleCustomer}>
                        {
                            customers?.map((e, index) =>
                                <Option
                                    key={String(e.id)}
                                    value={String(e?.id)}
                                >
                                    Id: {e?.id}, Name: {e?.name}
                                </Option>
                            )
                        }
                    </Select>
                </Form.Item>

                <Button htmlType={"submit"} type={"primary"}>Submit</Button>
            </Form>

            {customer &&
                <Form className="form-data" onFinish={fetchUpdateCustomer}>
                    <Form.Item label="Name" name={"name"}>
                        <Input value={name}
                               min={3}
                               max={32}
                               onChange={onNameChange}
                               placeholder="Enter customer`s name"
                        />
                    </Form.Item>

                    <Form.Item label="Email" name={"email"}>
                        <Input value={email}
                               min={5}
                               max={60}
                               onChange={onEmailChange}
                               placeholder="Enter customer`s email"
                               type={"email"}
                        />
                    </Form.Item>

                    <Form.Item label="Age" name={"age"}>
                        <Input value={age}
                               style={{width: "100%"}}
                               onChange={onAgeChange}
                               placeholder="Enter customer`s age"
                               type={"number"}
                        />
                    </Form.Item>

                    <Button htmlType={"submit"} type={"primary"}>Update customer</Button>
                </Form>
            }
        </div>
    );
};

export default UpdateCustomer;