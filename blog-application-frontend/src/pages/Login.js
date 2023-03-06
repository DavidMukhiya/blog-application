import React from 'react'
import { Container, Row, Col, Card, CardHeader, CardBody, Form, FormGroup, Label, Input, Button } from 'reactstrap'

const Login = () => {
  return (
    <Container>
      <Row className='mt-4' >
        <Col lg={{size:6, offset:3}} >
          <Card color='dark' inverse>
            <CardHeader>
             <h3>Login Here !!</h3>
            </CardHeader>
            <CardBody>
              <Form>
                {/* Email Field */}
                <FormGroup>
                  <Label for='email' >
                    Enter Email
                  </Label>
                  <Input type='text' id='email'/>
                </FormGroup>

                {/* Password Field */}
                <FormGroup>
                  <Label for='password'>
                    Enter Password
                  </Label>
                  <Input type='password' id='password' />
                </FormGroup>

                <Container className='text-center'>
                  <Button outline color='light' >Login</Button>
                  <Button className='ms-2' color='secondary' type='reset' outline>Reset</Button>
                </Container>
              </Form>
            </CardBody>
          </Card>
        </Col>
      </Row>
    </Container>
  )
}

export default Login