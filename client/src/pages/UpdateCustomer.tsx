import React, {useEffect, useState} from 'react';
import {useTypedSelector} from "../hooks/useTypedSelector";
import Loading from "../components/Loading";
import Error from "../components/Error";
import {useActions} from "../hooks/useActions";
import {Customer} from "../types/customer";
import {Button, Form, Select} from "antd";
import {rules} from "../utils/rools";
import {Option} from "antd/es/mentions";

const UpdateCustomer = () => {
    const {customers, loading, error} = useTypedSelector(state => state.customer);

    const {updateCustomer} = useActions();

    const [id, setId] = useState<number>(0);

    const handleCustomer = (e: React.ChangeEvent<HTMLSelectElement>) => setId(+e);

    // const fetchUpdateCustomer = () => updateCustomer();

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

            <Form className={"form-data"}>
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
        </div>
    );
};

export default UpdateCustomer;