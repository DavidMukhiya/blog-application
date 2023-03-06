import React from "react";
import {
  Container,
  Card,
  CardHeader,
  CardBody,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
  Row,
  Col,
} from "reactstrap";

const Signup = () => {
  return (
    <Container>
      <Row className="mt-4" >
        <Col sm={{ size: 12, offset: 0}} md={{size:6, offset:3}} lg={{size:6, offset:3}} outline="true" >
          <Card color="dark" inverse>
            <CardHeader>
              <h3>Fill Information to Register !!</h3>
            </CardHeader>
            <CardBody>
              <Form>
                {/* Name Field */}
                <FormGroup>
                  <Label for="name">Enter Name</Label>
                  <Input type="text" placeholder="Enter here" id="name" />
                </FormGroup>

                {/* Email Field */}
                <FormGroup>
                  <Label for="email">Enter Email</Label>
                  <Input type="email" placeholder="Enter here" id="email" />
                </FormGroup>

                {/* Password Field */}
                <FormGroup>
                  <Label for="password">Enter Password</Label>
                  <Input
                    type="password"
                    placeholder="Enter here"
                    id="password"
                  />
                </FormGroup>

                {/* About Field */}
                <FormGroup>
                  <Label for="about">Enter Name</Label>
                  <Input
                    type="textarea"
                    placeholder="Enter here"
                    id="about"
                    style={{ height: "250px" }}
                  />
                </FormGroup>

                <Container className="text-center">
                  <Button outline color="light">Register</Button>
                  <Button color="secondary" type="reset" className="ms-2">
                    Reset
                  </Button>
                </Container>
              </Form>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default Signup;
